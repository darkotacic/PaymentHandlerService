package ftn.sep.tim2.prh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUri {
	
	@Value("${spring.data.database}")
	private String databaseUri;
	
	@Value("${spring.data.pcc}")
	private String pccUri;
	
	@Value("${spring.data.pm}")
	private String pcmUri;
	
	@Value("${spring.data.merchantId}")
	private String merchantId;
	
	@Value("${spring.data.merchantPassword}")
	private String merchantPassword;
	
	public DatabaseUri(){
		
	}

	public String getPccUri() {
		return pccUri;
	}

	public void setPccUri(String pccUri) {
		this.pccUri = pccUri;
	}

	public String getDatabaseUri() {
		return databaseUri;
	}

	public void setDatabaseUri(String databaseUri) {
		this.databaseUri = databaseUri;
	}

	public String getPcmUri() {
		return pcmUri;
	}

	public void setPcmUri(String pcmUri) {
		this.pcmUri = pcmUri;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
}
