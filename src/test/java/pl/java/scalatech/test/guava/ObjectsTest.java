package pl.java.scalatech.test.guava;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
/**
 * @author przodownik
 * Module name :    java7features
 * Creating time :  30 maj 2014
 */
@Slf4j
public class ObjectsTest {
    @Test
    public void shouldHashCodeGenerate() {
         int hash = Objects.hash("slawek");
         int hash1 = Objects.hashCode("slawek");
         log.info("java : hash {}",hash);
         log.info("java : hashCode {}",hash1);
    }
    
    @Test
    public void shouldHashCodeGenerateGuavaWay() {
         int hash = com.google.common.base.Objects.hashCode("slawek");
         int hash1 = Objects.hashCode("slawek");
         
         log.info("guava  : hashCode {}",hash);
         log.info("java : hashCode {}",hash1);
    }
}
