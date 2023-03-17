package com.ganjunhao.aspect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.ganjunhao.annotation.ValidateExcel;
import com.ganjunhao.constant.ResultCode;
import com.ganjunhao.exception.BizException;
import com.ganjunhao.pojo.dto.ExcelImportDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;

/**
 * 校验文件类型是否为Excel
 **/
@Aspect
@Component
public class ValidateExcelAspect {

    @Around("@annotation(com.ganjunhao.annotation.ValidateExcel)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ValidateExcel annotation = method.getAnnotation(ValidateExcel.class);
        if (annotation == null) {
            return point.proceed();
        }
        //参数名数组
        Class[] parameterTypes = methodSignature.getParameterTypes();
        //参数值
        Object[] args = point.getArgs();
        //获取参数名对应数组下标
        int paramIndex = ArrayUtil.indexOf(parameterTypes, ExcelImportDTO.class);
        if (paramIndex != -1) {
            ExcelImportDTO dto = BeanUtil.copyProperties(args[paramIndex], ExcelImportDTO.class);
            if (dto != null) {
                MultipartFile file = dto.getFile();
                if (file != null) {
                    String originalFilename = file.getOriginalFilename();
                    //通过魔数判断是Excel文件
                    if (originalFilename != null && !originalFilename.endsWith(".xls") && !originalFilename.endsWith(".xlsx")) {
                        throw new BizException(ResultCode.MEDIA_TYPE_NOT_SUPPORTED, "不支持非Excel文件");
                    }
                }
            }
        }
        return point.proceed();
    }
}
