package com.example.springbootapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${APP_COLOR:black}")
    private String appColor;

    @GetMapping("/")
    public String main() {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='en'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Hello Page</title>");
        html.append("<style>");
        html.append("body {");
        html.append("  margin: 0;");
        html.append("  padding: 0;");
        html.append("  font-family: 'Segoe UI', sans-serif;");
        html.append("  display: flex;");
        html.append("  justify-content: center;");
        html.append("  align-items: center;");
        html.append("  height: 100vh;");
        html.append("  background: #f5f5f5;");
        html.append("}");
        html.append(".container {");
        html.append("  text-align: center;");
        html.append("  background: white;");
        html.append("  padding: 40px;");
        html.append("  border-radius: 12px;");
        html.append("  box-shadow: 0 10px 30px rgba(0,0,0,0.1);");
        html.append("}");
        html.append(".message {");
        html.append("  font-size: 2rem;");
        html.append("  color: ").append(appColor).append(";");
        html.append("  margin-bottom: 10px;");
        html.append("}");
        html.append(".subtitle {");
        html.append("  font-size: 1rem;");
        html.append("  color: #555;");
        html.append("}");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class='container'>");
        html.append("<div class='message'>Hello World !!!</div>");
        html.append("<div class='subtitle'>Powered by Spring Boot</div>");
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    @GetMapping("/healthz")
    public String probe() {
        return "{\"status\": \"ok\"}";
    }
}
