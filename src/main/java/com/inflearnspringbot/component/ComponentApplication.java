package com.inflearnspringbot.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import out.OutOfPackageServie;

import java.util.function.Supplier;

@SpringBootApplication
public class ComponentApplication {
/*
    //static한 방법
    public static void main(String[] args) {
        SpringApplication.run(ComponentApplication.class, args);
    }
*/
    //Springboot로 Application를 구동하는 또 다른 방법 (빌더 사용, 인스턴스 사용 방법)

    @Autowired
    OutOfPackageServie outOfPackageServie;

    public static void main(String[] args) {
        //인스턴스 사용 방법
        var app = new SpringApplication(ComponentApplication.class);
        //var : java10부터 지원되는 local variable 사용 방법 중 하나

        //중간에 무언가를 추가하고 싶다
        //initializer의 경우, 자신이 원하는 ApplicationContext를 주입받을 수 있는 기능
        app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) applicationContext -> {
            applicationContext.registerBean(OutOfPackageServie.class); //registerBean : 직접 빈을 등록할 때 사용
            //이렇게 직접 등록하면 @Service를 사용 필요 X

            //응용 (Funcatinal 한 Bean 등록)
            applicationContext.registerBean(ApplicationRunner.class, () -> args1 -> System.out.println("Funcational Bean Definition"));

            //이렇게 사용할 경우 각각의 bean에 대해 programming 한 방법으로 처리 가능 (on funcatinal + 구축시간 단축 가능)
        });
        app.run(args); //그냥 실행됨
    }


}
