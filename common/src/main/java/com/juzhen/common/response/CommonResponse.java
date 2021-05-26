package com.juzhen.common.response;

/**
 * @Description: �Զ�����Ӧ���ݽṹ
 * 				��������ṩ���Ż���ios����׿��΢���̳��õ�
 * 				�Ż����ܴ������ݺ���Ҫʹ�ñ���ķ���ת���ɶ��ڵ��������͸�ʽ���࣬����list��
 * 				�������д���
 * 				200����ʾ�ɹ�
 * 				500����ʾ���󣬴�����Ϣ��msg�ֶ���
 * 				501��bean��֤���󣬲��ܶ��ٸ�������map��ʽ����
 * 				502�����������ص��û�token����
 * 				555���쳣�׳���Ϣ
 */

public class CommonResponse {

    // ��Ӧҵ��״̬
    private Integer status;

    // ��Ӧ��Ϣ
    private String msg;

    // ��Ӧ�е�����
    private Object data;

    private String ok;	// ��ʹ��

    public static CommonResponse build(Integer status, String msg, Object data) {
        return new CommonResponse(status, msg, data);
    }

    public static CommonResponse ok(Object data) {
        return new CommonResponse(data);
    }

    public static CommonResponse ok() {
        return new CommonResponse(null);
    }

    public static CommonResponse errorMsg(String msg) {
        return new CommonResponse(500, msg, null);
    }

    public static CommonResponse errorMap(Object data) {
        return new CommonResponse(501, "error", data);
    }

    public static CommonResponse errorTokenMsg(String msg) {
        return new CommonResponse(502, msg, null);
    }

    public static CommonResponse errorException(String msg) {
        return new CommonResponse(555, msg, null);
    }

    public CommonResponse() {

    }

//    public static LeeJSONResult build(Integer status, String msg) {
//        return new LeeJSONResult(status, msg, null);
//    }

    public CommonResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
