package com.example.demo.jacksondemo.view;

public class View {

	//定义了一种视图，定义成interface就好了，不需要实现
	public interface Summary {}

	//定义了SummaryWithRecipients的视图，注意这个视图extends了Summary。所以这个视图包含了Summary视图。也就是，输出这个视图时候，同时输出Summary视图。
	public interface SummaryWithRecipients extends Summary {}

}
