package ee.ttu.repository;

import ee.ttu.configuration.EmbeddedDatasourceConfiguration;
import ee.ttu.configuration.PersistenceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by Vahur Kaar on 22.09.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {PersistenceConfiguration.class, EmbeddedDatasourceConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class RepositoryTestSupport extends AbstractTransactionalJUnit4SpringContextTests {
}
