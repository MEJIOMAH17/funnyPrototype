package prototype.not_prototype;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;
import prototype.not_prototype.fun.Prototype;
import prototype.not_prototype.fun.ShouldBePrototype;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class NotPrototypeApplicationTests {
    @Autowired
    ObjectFactory<Prototype> prototypeFactory;
    @Autowired
    ObjectFactory<ShouldBePrototype> shouldBePrototypeFactory;

    @Test
    public void ok() {
        assert (prototypeFactory.getObject()!= prototypeFactory.getObject());
    }

    @Test
    public void notOk() {
        assert (shouldBePrototypeFactory.getObject() != shouldBePrototypeFactory.getObject());
    }

}
