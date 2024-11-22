package StyleSync.demo.comn;

public enum ResultCode {
    SUCCESS("200", "Success"),
    ERROR("500", "Internal Server Error");

    private final String resultCode;
    private final String resultMsg;

    ResultCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}