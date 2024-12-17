package dev.alexanderkg.budget_backend;

public record CreateItemRequestBody(String name, Double price, String category) {
}
