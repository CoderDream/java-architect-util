package com.example.demo.jacksondemo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.jacksondemo.service.MessageService;
import com.example.demo.jacksondemo.data.User;
import com.example.demo.jacksondemo.data.Message;

@Service
public class MessageServiceImpl implements MessageService{

	private List<Message> messages = new ArrayList<>();

	@Autowired
	private ObjectMapper objectMapper;

	public MessageServiceImpl() {
		User brian = new User(1L, "Brian", "Clozel", "bclozel@pivotal.io", "1 Jaures street", "69003", "Lyon", "France");
		User stephane = new User(2L, "Stéphane", "Nicoll", "snicoll@pivotal.io", "42 Obama street", "1000", "Brussel", "Belgium");
		User rossen = new User(3L, "Rossen", "Stoyanchev", "rstoyanchev@pivotal.io", "3 Warren street", "10011", "New York", "USA");

		Message info = new Message(1L, "Info", "This is an information message", brian, stephane, rossen);
		Message warning = new Message(2L, "Warning", "This is a warning message", stephane, rossen);
		Message alert = new Message(3L, "Alert", "This is an alert message", rossen, brian);

		messages.add(info);
		messages.add(warning);
		messages.add(alert);
	}

	@Override
	public List<Message> getAll() {
		return messages;
	}

	@Override
	public Message get(Long id) {
		return this.messages.stream().filter((m) -> m.getId().equals(id)).findFirst().get();
	}

	@Override
	public Message create(Message message) {
		this.messages.add(message);
		return message;
	}

	@Override
	public String loadFile(String filePath) throws FileNotFoundException, IOException {
		//这里如果读取的文件内容没问题，就要覆盖掉messages中的内容
		messages = objectMapper.readValue(new File(filePath),new TypeReference<List<Message>>(){});
		return IOUtils.toString(new FileInputStream(filePath), "UTF-8");
	}

	@Override
	public String saveIntoFile(String filePath) throws IOException {
		//这里messages用Jackson序列化
		objectMapper.writeValue(new File(filePath), messages);
		return "success";
	}
}
