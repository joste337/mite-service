package de.jos.service.mite.miteservice.controller;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import de.jos.service.mite.miteservice.model.MiteRequestAttributes;
import de.jos.service.mite.miteservice.model.MiteServiceReply;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MiteSeviceTest {
    @Autowired
    private MiteSevice miteSevice;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(miteSevice, "miteBaseUrl", "http://localhost:" + wireMockRule.port() + "/");
    }

    @Test
    public void shouldReturnSuccessForNewEntry() {
        MiteServiceReply miteServiceReply = miteSevice.createNewEntry(aMiteRequestAttribute());

        assertThat(miteServiceReply.isSuccess()).isTrue();
    }

    private MiteRequestAttributes aMiteRequestAttribute() {
        MiteRequestAttributes miteRequestAttributes = new MiteRequestAttributes();
        miteRequestAttributes.setApiKey("527f1fde5d5609c5");
        miteRequestAttributes.setComment("new entry");
        miteRequestAttributes.setDuration("60");
        miteRequestAttributes.setProjectId("2351287");
        miteRequestAttributes.setServiceId("253445");
        return miteRequestAttributes;
    }
}
