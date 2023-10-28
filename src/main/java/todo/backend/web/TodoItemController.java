package todo.backend.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import todo.backend.mapper.TodoItemMapper;
import todo.backend.model.dto.TodoItemDto;
import todo.backend.service.api.TodoItemService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo-management")
public class TodoItemController {
    private final TodoItemMapper todoItemMapper;
    private final TodoItemService todoItemService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoItemDto retrieve(@PathVariable UUID id) {
        return todoItemMapper.toDto(todoItemService.getById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoItemDto> getList() {
        return todoItemMapper.toDto(todoItemService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItemDto create(@RequestBody TodoItemDto todoItem) {
        return todoItemMapper.toDto(todoItemService.create(todoItemMapper.toEntity(todoItem)));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoItemDto patch(@PathVariable UUID id, @RequestBody TodoItemDto todoItem) {
        todoItem.setId(id);
        return todoItemMapper.toDto(todoItemService.patch(todoItemMapper.toEntity(todoItem)));
    }
}