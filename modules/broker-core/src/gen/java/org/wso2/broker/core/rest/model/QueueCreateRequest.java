package org.wso2.broker.core.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class QueueCreateRequest   {
  
  private @Valid String name = null;
  private @Valid Boolean durable = null;
  private @Valid Boolean autoDelete = null;

  /**
   * Name of the queue to be created
   **/
  public QueueCreateRequest name(String name) {
    this.name = name;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "Name of the queue to be created")
  @JsonProperty("name")
  @NotNull
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   * True if the queue is durable, false otherwise
   **/
  public QueueCreateRequest durable(Boolean durable) {
    this.durable = durable;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "True if the queue is durable, false otherwise")
  @JsonProperty("durable")
  @NotNull
  public Boolean isDurable() {
    return durable;
  }
  public void setDurable(Boolean durable) {
    this.durable = durable;
  }

  /**
   * deletes the queue when there are no consumers for the queue.
   **/
  public QueueCreateRequest autoDelete(Boolean autoDelete) {
    this.autoDelete = autoDelete;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "deletes the queue when there are no consumers for the queue.")
  @JsonProperty("autoDelete")
  @NotNull
  public Boolean isAutoDelete() {
    return autoDelete;
  }
  public void setAutoDelete(Boolean autoDelete) {
    this.autoDelete = autoDelete;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueueCreateRequest queueCreateRequest = (QueueCreateRequest) o;
    return Objects.equals(name, queueCreateRequest.name) &&
        Objects.equals(durable, queueCreateRequest.durable) &&
        Objects.equals(autoDelete, queueCreateRequest.autoDelete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, durable, autoDelete);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueueCreateRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    durable: ").append(toIndentedString(durable)).append("\n");
    sb.append("    autoDelete: ").append(toIndentedString(autoDelete)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

