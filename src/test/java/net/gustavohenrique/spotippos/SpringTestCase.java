package net.gustavohenrique.spotippos;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/META-INF/spring/applicationContext-test.xml")
public abstract class SpringTestCase extends TestCase {

}
