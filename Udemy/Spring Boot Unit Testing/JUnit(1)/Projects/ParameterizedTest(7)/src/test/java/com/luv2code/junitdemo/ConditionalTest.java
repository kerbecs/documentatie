package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("Don't run until JIRA #123 is resolved")
    void basicTest(){

    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly(){

    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMac(){

    }

    @Test
    @EnabledOnOs({OS.WINDOWS,OS.LINUX})
    void testForLinuxAndWindows(){

    }

    @Test
    @EnabledOnJre(JRE.JAVA_18)
    void forJre18(){

    }
    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void forJre17(){

    }
    @Test
    @EnabledOnJre(JRE.OTHER)
    void otherJava(){

    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_12,max = JRE.JAVA_17)
    void forJreFrom12Till17(){

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "LUV2CODE_ENV",matches = "*DEV")
    void testForDevEnv(){

    }
    @Test
    @EnabledIfSystemProperty(named = "LUV2CODE_SYS_PROP",matches = "*CI_CD_DEPLOY")
    void testForSystemProp(){

    }
}
