package com.my.wallet.web.utils;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

public class ListBaseBean {
	private String itemName;			//list中子节点名称
	
	private Long total;			//查询结果总数
	
	private Boolean hasMore; //是否还有数据，分页时根据是否为最后一页返回，不分页默认为false
	
	private List<Object> list;	//列表数据
	
	private Map<String,Object> properties;

	public ListBaseBean(List list,String itemName, Boolean hasMore) {
		super();
		this.itemName = itemName==null ? "item" : itemName;
		this.hasMore = hasMore==null ? false:hasMore;
		this.list = list;
	}

	public Boolean getHasMore() {
		return hasMore;
	}

	public void setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}


	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}


	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

    /***
	 * 生成list对象的xml方法
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public StringBuffer converXml(String name) throws Exception{
		StringBuffer sf = new StringBuffer();
		int size = list==null? 0 : list.size();
		
		
		sf.append("<list ");
		if(StringUtils.isNotEmpty(name)){
			sf.append("name='");
			sf.append(name);
			sf.append("' ");
		}
		
		sf.append(this.getPropertiesXml());
		
		
		sf.append("hasMore='");
		sf.append(hasMore);
		sf.append("' size='");
		sf.append(size);
		sf.append("'>");
		
		if(list!=null){
			for(Object item:list){
				sf.append(ConverTool.conver(item, itemName));
			}
		}
		
		sf.append("</list>");
		return sf;
	}
	
	/**
	 * 生成list对象的json方法
	 * @param name
	 * @return
	 */
	public StringBuffer converJson(String name) {
		JSONObject json = null;
		if(StringUtils.isNotEmpty(name)){
			json = new JSONObject();
			json.put(name, JSONObject.fromObject(this,ConverTool.jsonConfig));
		}else{
			json = JSONObject.fromObject(this,ConverTool.jsonConfig);
		}
		return new StringBuffer(json.toString());
	}
	
	/***
	 * 生成LIST的其他属性
	 * @return
	 */
	private StringBuffer getPropertiesXml(){
		StringBuffer sf = new StringBuffer();
		if(properties==null) return sf;
		
		for(String key : properties.keySet()){
			String value = Utils.obj2str(properties.get(key));
			if(StringUtils.isNotEmpty(value)){
				sf.append(" ");
				sf.append(key);
				sf.append("='");
				sf.append(value);
				sf.append("' ");
			}
		}
		
		return sf;
	}
}
