package com.aqua.fortune.impl;

import com.aqua.fortune.Fortune;
import com.linkedin.data.DataMap;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;
import java.util.HashMap;
import java.util.Map;


@RestLiCollection(name = "fortunes", namespace = "com.aqua.fortune")
public class FortuneResource extends CollectionResourceTemplate<String, Fortune> {

  static Map<String, String> fortunes = new HashMap<>();
  static {
    fortunes.put("Chen", "Today is your lucky day.");
    fortunes.put("Li", "There's no time like the present.");
    fortunes.put("Fang", "Don't worry, be happy.");
  }

  @Override
  public Fortune get(String name) {
    try {
      final String message = fortunes.get(name);
      return new Fortune().setName(name).setMessage(message);
    } catch (Exception exception) {
      final Map<String, String> dataMap = new HashMap<>();
      dataMap.put("name", "Bob");
      dataMap.put("message", "Your luck has run out.");
      return new Fortune(new DataMap(dataMap));
    }
  }
}