package es.bcnc.hexagonal.servicios.bootsstrap;

import es.bcnc.hexagonal.servicios.SpringAppConfig;
import org.springframework.boot.SpringApplication;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(SpringAppConfig.class,args);
    }
}