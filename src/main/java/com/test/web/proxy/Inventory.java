package com.test.web.proxy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component//값을 여러개 담고 빼는 거니까! bean으로 사용!
public class Inventory<T> {
	private ArrayList<T> inventory;
	public Inventory() {inventory= new ArrayList<>();}
	public void add(T t) {
		inventory.add(t);
	}
	public void clear() {
		inventory.clear();
	}
	public T get (int i) {
		return inventory.get(i);
	}
	public ArrayList<T> get(){
		return inventory;
	}
}
