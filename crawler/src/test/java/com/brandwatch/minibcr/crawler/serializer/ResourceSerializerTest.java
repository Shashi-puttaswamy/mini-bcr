package com.brandwatch.minibcr.crawler.serializer;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.brandwatch.minibcr.common.model.Resource;

@SpringBootTest
public class ResourceSerializerTest {
    @Test
    public final void testSerialize() {
        try (ResourceSerializer resourceSerializer = new ResourceSerializer()) {
            Resource resource = new Resource("testTopic", "testText");
            byte[] data = resourceSerializer.serialize("", resource);
            Assert.assertTrue(new String(data).contains("testTopic"));
        }
    }
}
