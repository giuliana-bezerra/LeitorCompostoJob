package com.example.leitorcomposto.reader;

import java.util.List;

public interface PageProcessor<T> {
  void process(List<T> page);
}
