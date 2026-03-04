package com.fastag.common.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fastag.common.entity.Tag;
import com.fastag.common.repository.TagRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehicleTagService {

	private static final String PREFIX = "vehicle:";

	private final RedisTemplate<String, Object> redisTemplate;
	private final TagRepository repository;

	public Tag getVehicleTag(String tagId) {

		// Try Redis
		Tag tag = getTagFromRedis(tagId);

		if (tag != null) {
			System.out.println("Cache HIT");
			return tag;
		}

		// DB fallback
		System.out.println("Cache MISS → DB call");

		tag = repository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));

		// Store in Redis
		saveToRedis(tag);

		return tag;
	}

	public String getKey(String tagId) {
		String key = PREFIX + tagId;
		return key;
	}

	public Tag getTagFromRedis(String tagId) {
		Tag tag = (Tag) redisTemplate.opsForValue().get(getKey(tagId));
		return tag;
	}

	public void saveToRedisAndDB(Tag tag) {
		saveToRedis(tag);
		repository.save(tag);
	}

	public void saveToRedis(Tag tag) {
		// Store in Redis (Time Limit - 1 Day)
		redisTemplate.opsForValue().set(getKey(tag.getTagId()), tag, 1, TimeUnit.DAYS);
	}
}