package cn.ityihang.zblog.common;


public class RespResult {
	private Integer status;
	private String msg;
	private Object data;

	public static RespResult build() {
		return new RespResult();
	}

	public static RespResult ok(String msg) {
		return new RespResult(200, msg, null);
	}

	public static RespResult ok(String msg, Object obj) {
		return new RespResult(200, msg, obj);
	}

	public static RespResult error(String msg) {
		return new RespResult(500, msg, null);
	}

	public static RespResult error(String msg, Object obj) {
		return new RespResult(500, msg, obj);
	}


	private RespResult() {
	}

	private RespResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {

		return status;
	}

	public RespResult setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RespResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public RespResult setData(Object data) {
		this.data = data;
		return this;
	}
}
