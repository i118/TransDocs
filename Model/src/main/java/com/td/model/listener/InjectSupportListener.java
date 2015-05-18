package com.td.model.listener;

import com.td.model.context.SpringContextHolder;

import javax.annotation.PostConstruct;

/**
 * Created by zerotul on 07.04.15.
 */
public abstract class InjectSupportListener {

    public InjectSupportListener(){
        inject();
    }

    private void inject(){
        SpringContextHolder.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
    }
}
