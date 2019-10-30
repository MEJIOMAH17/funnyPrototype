package prototype.not_prototype;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AopTestUtils;
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
        assert (prototypeFactory.getObject() != prototypeFactory.getObject());
    }

    @Test
    public void notOk() {
        assert (shouldBePrototypeFactory.getObject() != shouldBePrototypeFactory.getObject());
    }

    /**
     * https://github.com/spring-projects/spring-framework/issues/23725#issuecomment-546374463
     */
    @Test
    public void sbrannenRecommendation() {
        ShouldBePrototype proxy1 = shouldBePrototypeFactory.getObject();
        ShouldBePrototype proxy2 = shouldBePrototypeFactory.getObject();

        ShouldBePrototype target1 = AopTestUtils.getUltimateTargetObject(proxy1);
        ShouldBePrototype target2 = AopTestUtils.getUltimateTargetObject(proxy2);

        assert (target1 != target2);

        ShouldBePrototype shouldBeEqualTarget1 = AopTestUtils.getUltimateTargetObject(proxy1);

        assert (shouldBeEqualTarget1 == target1);

    }

}
