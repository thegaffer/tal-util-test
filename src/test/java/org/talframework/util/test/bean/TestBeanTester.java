package org.talframework.util.test.bean;

import org.junit.Test;

public class TestBeanTester {

    @Test
    public void simple() {
        BeanTester.testBean(SimpleBean.class);
    }
}
