package com.fonyou.finaltest.util.properties;

import com.fonyou.finaltest.config.CustomConfigWatcher;
import com.fonyou.root.utils.lib.interfaces.GsonUtilsInterface;
import com.fonyou.root.utils.lib.interfaces.UtilsInterface;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageProperties implements GsonUtilsInterface, UtilsInterface
{
    private CustomConfigWatcher config;

    public MessageProperties(CustomConfigWatcher config) {
        this.config = config;
    }


    // Generic response message
    private String okMsg;
    private String failedMsg;

    public String getOkMsg()
    {
        return okMsg;
    }

    public void setOkMsg(String okMsg)
    {
        this.okMsg = okMsg;
    }

    public String getFailedMsg()
    {
        return failedMsg;
    }

    public void setFailedMsg(String failedMsg)
    {
        this.failedMsg = failedMsg;
    }

    @PostConstruct
    public void init()
    {
        this.setOkMsg(this.getValidateConfig(this.config.get("main.message.okMsg"), "Respuesta default Exitosa"));
        this.setFailedMsg(this.getValidateConfig(this.config.get("main.message.failedMsg"), "Respuesta default Fallida"));
        System.out.println("OkMsg = " + this.getOkMsg());
        System.out.println("failedMsg = " + this.getFailedMsg());
    }

    private String getValidateConfig(String resultConfig, String defaultValue)
    {
        return resultConfig == null ? defaultValue : resultConfig;
    }

    @Override
    public Logger getLogger()
    {
        return GsonUtilsInterface.super.getLogger();
    }
}
