package com.tinyego.chatgpt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Choice[] choices;
    private Usage usage;
    private String system_fingerprint ;
    
    @Getter
    @Setter
	public static class Choice {
        private int index;
        private Message message;
        private Object logprobs;
        private String finish_reason;
  
        @Getter
        @Setter
		public static class Message {
            private String role;
            private String content;
        }		
    }
	
    @Getter
    @Setter
	public static class Usage{
		private int prompt_tokens;
		private int completion_tokens;
		private int total_tokens;
	}
}