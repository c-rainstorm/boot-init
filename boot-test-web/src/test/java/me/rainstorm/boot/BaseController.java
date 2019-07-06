package me.rainstorm.boot;

import com.fasterxml.jackson.core.type.TypeReference;
import me.rainstorm.boot.domain.util.JsonUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseController {
    @Resource
    private WebApplicationContext webApplicationContext;
    @Resource
    private MockMvc mockMvc;

    @Before
    public void setWebApplicationContext() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected <T> T post(String url, Object request, Class<T> kls) throws Exception {
        String content = post(url, request);

        return JsonUtil.fromJson(content, kls);
    }

    protected <T> T post(String url, Object request, TypeReference<T> typeReference) throws Exception {
        String content = post(url, request);

        return JsonUtil.fromJson(content, typeReference);
    }

    private String post(String url, Object request) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(JsonUtil.toJsonString(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    protected <T> T get(String url, Class<T> kls) throws Exception {
        String content = get(url);

        return JsonUtil.fromJson(content, kls);
    }

    protected <T> T get(String url, TypeReference<T> typeReference) throws Exception {
        String content = get(url);

        return JsonUtil.fromJson(content, typeReference);
    }

    private String get(String url) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
