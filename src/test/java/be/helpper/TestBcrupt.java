package be.helpper;

import io.quarkus.elytron.security.common.BcryptUtil;

public class TestBcrupt {
    public static void Main() {
        System.out.println(BcryptUtil.bcryptHash("123"));
    }
}
