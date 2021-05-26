package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.dto.voucher.*;
import com.soulroomie.demo.consumer.service.mapper.voucher.BillerMapper;
import com.soulroomie.demo.consumer.service.mapper.voucher.ServiceTypeMapper;
import com.soulroomie.demo.consumer.service.mapper.voucher.VoucherBookingMapper;
import com.soulroomie.demo.consumer.service.mapper.voucher.VoucherCustomerMapper;
import com.soulroomie.demo.consumer.service.model.voucher.BillerInformationModel;
import com.soulroomie.demo.consumer.service.model.voucher.CustomerModel;
import com.soulroomie.demo.consumer.service.model.voucher.ServiceTypeModel;
import com.soulroomie.demo.consumer.service.model.voucher.VoucherBookingModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class VoucherService {
    @Resource
    JavaMailSender javaMailSender;
    @Resource
    VoucherCustomerMapper voucherCustomerMapper;
    @Resource
    BillerMapper billerMapper;
    @Resource
    VoucherBookingMapper voucherBookingMapper;
    @Resource
    ServiceTypeMapper serviceTypeMapper;

    public Result customerLogin(LoginCreDto loginCreDto) {
        LambdaQueryWrapper<CustomerModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerModel::getEmail, loginCreDto.getEmail());
        queryWrapper.eq(CustomerModel::getPassword, loginCreDto.getPassword());
        CustomerModel customerModel = voucherCustomerMapper.selectOne(queryWrapper);
        if (customerModel != null){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    public Result adminLogin(LoginCreDto loginCreDto) {
        if (loginCreDto.getEmail().equals("admin")&&loginCreDto.getPassword().equals("admin")){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    public Result customerRegister(CustomerRegisterDto customerRegisterDto) {
        CustomerModel customerModel = CustomerModel.builder().email(customerRegisterDto.getEmail()).
                name(customerRegisterDto.getName()).password(customerRegisterDto.getPassword()).
                phone(customerRegisterDto.getPhone()).build();
        System.out.println(customerModel.toString());
        voucherCustomerMapper.insert(customerModel);
        return Result.ok();
    }

    public Result updateInformation(CustomerRegisterDto customerRegisterDto) {
        LambdaQueryWrapper<CustomerModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerModel::getEmail,customerRegisterDto.getEmail());
        CustomerModel customerModel = CustomerModel.builder().email(customerRegisterDto.getEmail()).
                name(customerRegisterDto.getName()).password(customerRegisterDto.getPassword()).
                phone(customerRegisterDto.getPhone()).build();
        voucherCustomerMapper.update(customerModel,queryWrapper);
        return Result.ok();
    }

    public Result addBiller(BillerInformationDto billerInformationDto) {
        BillerInformationModel billerInformationModel = BillerInformationModel.builder()
                .biller_email(billerInformationDto.getBillerEmail())
                .customer_email(billerInformationDto.getCustomerEmail())
                .name(billerInformationDto.getName()).build();
        billerMapper.insert(billerInformationModel);
        return Result.ok();
    }

    public Result updateBiller(BillerInformationDto billerInformationDto) {
        BillerInformationModel billerInformationModel = BillerInformationModel.builder()
                .biller_email(billerInformationDto.getBillerEmail())
                .customer_email(billerInformationDto.getCustomerEmail())
                .name(billerInformationDto.getName()).build();
        LambdaQueryWrapper<BillerInformationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BillerInformationModel::getBiller_email,billerInformationDto.getBillerEmail());
        queryWrapper.eq(BillerInformationModel::getCustomer_email,billerInformationDto.getCustomerEmail());
        billerMapper.update(billerInformationModel,queryWrapper);
        return Result.ok();
    }

    public Result bookVoucher(VoucherBookingDto voucherBookingDto) {
        VoucherBookingModel voucherBookingModel = VoucherBookingModel.builder()
                .customer_email(voucherBookingDto.getCustomerEmail())
                .date(voucherBookingDto.getDate())
                .delivery_mode(voucherBookingDto.getDeliveryMode())
                .msg(voucherBookingDto.getMsg())
                .accept("not")
                .service(voucherBookingDto.getService()).build();
        String emailAddress = voucherBookingDto.getCustomerEmail();
        LambdaQueryWrapper<CustomerModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerModel::getEmail,emailAddress);
        CustomerModel customerModel = voucherCustomerMapper.selectOne(queryWrapper);
        String date = voucherBookingDto.getDate();
        String msg = voucherBookingDto.getMsg();
        String phone = customerModel.getPhone();
        String name = customerModel.getName();
        voucherBookingMapper.insert(voucherBookingModel);
        String textEmail = "Customer Email Address : " +
                emailAddress +
                "\nPhone : " +
                phone +
                "\nName : " +
                name +
                "\nDate : " +
                date +
                "\nMessage : " +
                msg;
        sendEmail("Voucher Booking",textEmail,"932070769@qq.com");
        return Result.ok();
    }

    private void sendEmail(String subject, String text,String receiver) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setFrom("2872985302@qq.com");
        mailMessage.setTo(receiver);
        mailMessage.setSentDate(new Date());
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    public Result cancelVoucher(VoucherCancelDto voucherCancelDto) {
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VoucherBookingModel::getId,voucherCancelDto.getVoucherBookingId());
        VoucherBookingModel voucherBookingModel = voucherBookingMapper.selectOne(queryWrapper);
        voucherBookingMapper.delete(queryWrapper);
        LambdaQueryWrapper<CustomerModel> customerModelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        String email = voucherBookingModel.getCustomer_email();
        customerModelLambdaQueryWrapper.eq(CustomerModel::getEmail,email);
        String date = voucherBookingModel.getDate();
        CustomerModel customerModel = voucherCustomerMapper.selectOne(customerModelLambdaQueryWrapper);
        String name = customerModel.getName();
        String phone=customerModel.getPhone();
        String text = "Name : " + name + "\nPhone : "+phone+"\nEmail : "+email+"\nDate: "+date;
        sendEmail("Cancel Voucher Booking",text,"932070769@qq.com");
        return Result.ok();
    }

    public Result viewVoucher(VoucherViewDto voucherViewDto) {
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VoucherBookingModel::getCustomer_email,voucherViewDto.getCustomerEmailAddress());
        return Result.ok().data("voucherList",voucherBookingMapper.selectList(queryWrapper));
    }

    public Result acceptVoucherBooking(VoucherAcceptDto voucherAcceptDto) {
        VoucherBookingModel voucherBookingModel = VoucherBookingModel.builder().accept("accept").build();
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VoucherBookingModel::getId,voucherAcceptDto.getBookingId());
        VoucherBookingModel voucherBookingModel1 = voucherBookingMapper.selectOne(queryWrapper);
        String email = voucherBookingModel1.getCustomer_email();
        LambdaQueryWrapper<CustomerModel> customerQueryWrapper = new LambdaQueryWrapper<>();
        customerQueryWrapper.eq(CustomerModel::getEmail,email);
        CustomerModel customerModel = voucherCustomerMapper.selectOne(customerQueryWrapper);
        String name = customerModel.getName();
        String phone = customerModel.getPhone();
        String date = voucherBookingModel1.getDate();
        voucherBookingMapper.update(voucherBookingModel,queryWrapper);
        String text = "Name : " + name + "\nPhone : "+phone+"\nEmail : "+email+"\nDate: "+date;
        sendEmail("Voucher Accepted",text,email);
        return Result.ok();
    }

    public Result viewVoucherBooking() {
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        return Result.ok().data("voucherList",voucherBookingMapper.selectList(queryWrapper));
    }

    public Result addServiceType(AddServiceDto addServiceDto) {
        ServiceTypeModel serviceTypeModel = ServiceTypeModel.builder().name(addServiceDto.getName()).build();
        serviceTypeMapper.insert(serviceTypeModel);
        return Result.ok();
    }

    public Result viewServiceType() {
        LambdaQueryWrapper<ServiceTypeModel> queryWrapper = new LambdaQueryWrapper<>();
        return Result.ok().data("voucherList",serviceTypeMapper.selectList(queryWrapper));
    }

    public Result viewInformation(VoucherViewDto customerRegisterDto) {
        String email = customerRegisterDto.getCustomerEmailAddress();
        LambdaQueryWrapper<CustomerModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerModel::getEmail,email);
        return Result.ok().data("customer",voucherCustomerMapper.selectOne(queryWrapper));
    }
}
