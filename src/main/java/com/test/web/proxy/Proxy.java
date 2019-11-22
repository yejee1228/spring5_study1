package com.test.web.proxy;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component("pxy")
public class Proxy {
	public String string(Object param) {
		Function<Object, String> f = String :: valueOf;
		return f.apply(param);
	}
	public int integer(String param) {
		Function<String,Integer> f = Integer::parseInt;
		return f.apply(param);
	}
	public boolean equal(String s1, String s2) {
		BiPredicate<String,String> p = String :: equals;
		return p.test(s1,s2);
	}
	public void printer(String param) {
		Consumer<String> c = System.out :: println;
		c.accept(param);
	}
}
