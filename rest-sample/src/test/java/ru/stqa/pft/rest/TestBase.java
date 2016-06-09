package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Sasha on 09.06.2016.
 */
public class TestBase {

  protected Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    String state = getSelectedIssue(issueId).getState();
    return !Objects.equals(state, "Closed");
  }

  private Issue getSelectedIssue(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement selectedIssue = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
    return new Gson().fromJson(selectedIssue, Issue.class);
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
