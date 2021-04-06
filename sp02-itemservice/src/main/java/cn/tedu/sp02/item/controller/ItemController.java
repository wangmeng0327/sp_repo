package cn.tedu.sp02.item.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import cn.tedu.web.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ItemController {
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

	@Autowired
	private ItemService itemService;

	/**
	 * 为了后续测试，观察是哪一台服务器执行
	 * 把yml端口号注册到程序 中
	 */
	@Value("${server.port}")
	private int port;

	@GetMapping("/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
		log.info("server.port="+port+", orderId="+orderId);
		
		//随机的超时时长
		long t = new Random().nextInt(5000);

		//--设置随机延迟//
		if(Math.random()<0.6) { 
			log.info("item-service-"+port+" - 暂停 "+t);
			Thread.sleep(t);
		}



		List<Item> items = itemService.getItems(orderId);
		return JsonResult.ok(items).msg("port="+port);
	}

	@PostMapping("/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		itemService.decreaseNumbers(items);
		return JsonResult.ok();
	}
}
