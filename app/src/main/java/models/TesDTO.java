package models;

import java.io.Serializable;

import json.JSONAble;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;

public class TesDTO implements DTO, JSONAble, Serializable {

		private static final String tag = "TesDTO";
        public static final long serialVersionUID = 1L;
        public static final String USERID = "userId";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String COMPLETED = "completed";

        private String userId;
        private String id;
        private String title;
        private String completed;
		public TesDTO()
	    {
            userId="";
            id="";
            title="";
            completed="";
	    }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCompleted() {
            return completed;
        }

        public void setCompleted(String completed) {
            this.completed = completed;
        }

        public void fromJSON(String jsonString) {
                try {
                	JSONObject json = new JSONObject(jsonString);
                    setUserId(json.getString(USERID));
                    setId(json.getString(ID));
                    setTitle(json.getString(TITLE));
                    setCompleted(json.getString(COMPLETED));
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
        }

        public String toJSON() {
                JSONObject outer = new JSONObject();
                return outer.toString();
        }
}
