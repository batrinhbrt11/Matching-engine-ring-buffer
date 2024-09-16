package vn.demo.starter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.demo.starter.service.P2POrderService;
import vn.demo.starter.service.dto.P2POrderDto;
import vn.demo.starter.service.dto.request.P2POrderRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Order Resource")
public class OrderController {

    private final P2POrderService p2POrderService;

    @GetMapping
    public ResponseEntity<List<P2POrderDto>> getOrders() {
        return ResponseEntity.ok(p2POrderService.getOpenOrder());
    }

    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody P2POrderRequestDto request){
        p2POrderService.createOrder(request);
        return ResponseEntity.noContent().build();
    }
}
