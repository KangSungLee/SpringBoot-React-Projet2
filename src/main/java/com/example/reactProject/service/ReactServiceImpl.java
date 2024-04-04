package com.example.reactProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reactProject.dao.ReactDao;
import com.example.reactProject.entity.React;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReactServiceImpl implements ReactService {
	private final ReactDao reactDao;
	
	@Override
	public React getUserByUid(String uid) {
		return reactDao.gerUserByUid(uid);
	}

	@Override
	public List<React> getUserList(int page) {
		int count = COUNT_PER_PAGE;
		int offset = (page - 1) * COUNT_PER_PAGE;
		return reactDao.getUserList(count, offset);
	}

	@Override
	public int getUserCount() {
		return reactDao.getUserCount();
	}
	
}
