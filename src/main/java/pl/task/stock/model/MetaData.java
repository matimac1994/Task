
package pl.task.stock.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "1. Information",
        "2. Symbol",
        "3. Last Refreshed",
        "4. Output Size",
        "5. Time Zone"
})
public class MetaData {

    @JsonProperty("1. Information")
    private String _1Information;
    @JsonProperty("2. Symbol")
    private String _2Symbol;
    @JsonProperty("3. Last Refreshed")
    private String _3LastRefreshed;
    @JsonProperty("4. Output Size")
    private String _4OutputSize;
    @JsonProperty("5. Time Zone")
    private String _5TimeZone;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("1. Information")
    public String get1Information() {
        return _1Information;
    }

    @JsonProperty("1. Information")
    public void set1Information(String _1Information) {
        this._1Information = _1Information;
    }

    @JsonProperty("2. Symbol")
    public String get2Symbol() {
        return _2Symbol;
    }

    @JsonProperty("2. Symbol")
    public void set2Symbol(String _2Symbol) {
        this._2Symbol = _2Symbol;
    }

    @JsonProperty("3. Last Refreshed")
    public String get3LastRefreshed() {
        return _3LastRefreshed;
    }

    @JsonProperty("3. Last Refreshed")
    public void set3LastRefreshed(String _3LastRefreshed) {
        this._3LastRefreshed = _3LastRefreshed;
    }

    @JsonProperty("4. Output Size")
    public String get4OutputSize() {
        return _4OutputSize;
    }

    @JsonProperty("4. Output Size")
    public void set4OutputSize(String _4OutputSize) {
        this._4OutputSize = _4OutputSize;
    }

    @JsonProperty("5. Time Zone")
    public String get5TimeZone() {
        return _5TimeZone;
    }

    @JsonProperty("5. Time Zone")
    public void set5TimeZone(String _5TimeZone) {
        this._5TimeZone = _5TimeZone;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}