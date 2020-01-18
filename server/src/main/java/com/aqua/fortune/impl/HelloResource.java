package com.aqua.fortune.impl;

import com.linkedin.data.DataMap;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;
import com.aqua.fortune.Hello;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.xml.crypto.Data;


@RestLiCollection(name = "hello", namespace = "com.aqua.fortune")
public class HelloResource extends CollectionResourceTemplate<String, Hello> {

  static Map<String, String> fortunes = new HashMap<>();
  static {
    fortunes.put("Chen", "Today is your lucky day.");
    fortunes.put("Li", "There's no time like the present.");
    fortunes.put("Fang", "Don't worry, be happy.");
  }

  @Override
  public Hello get(String name) {
    try {
      final String message = fortunes.get(name);
      return new Hello().setName(name).setMessage(message);
    } catch (Exception exception) {
      final Map<String, String> dataMap = new HashMap<>();
      dataMap.put("name", "Bob");
      dataMap.put("message", "Your luck has run out.");
      return new Hello(new DataMap(dataMap));
    }
  }
}