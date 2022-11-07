package com.example.rest.webservices.restfullwebservices.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties("field1")
class SomeBean{
    private String field1;
    @JsonIgnore
    private String field2;
    private String field3;
}

@Getter
@Setter
@AllArgsConstructor
@JsonFilter("SomeBean2Filter")
class SomeBean2{
    private String field1;
    private String field2;
    private String field3;
}
@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean filtering(){
        return new SomeBean("value1","value2","value3");
    }
    @GetMapping("/filteringList")
    public List<SomeBean> filteringList(){
        return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
    }

    @GetMapping("/filtering2")
    public MappingJacksonValue filtering2(){
        SomeBean2 bean2 = new SomeBean2("value1","value2","value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean2);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean2Filter", filter);

        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering2List")
    public MappingJacksonValue filtering2List(){
        List<SomeBean2> list = Arrays.asList(new SomeBean2("value1","value2","value3"),new SomeBean2("value4","value5","value6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean2Filter", filter);

        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

}
