package org.example.testtask1221.controller;

import lombok.RequiredArgsConstructor;
import org.example.testtask1221.model.MealsReport;
import org.example.testtask1221.model.DailyReport;
import org.example.testtask1221.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @GetMapping("/summary/{user_id}/{date}")
  ResponseEntity<DailyReport> dailySummaryOfUser(@PathVariable("user_id") Long userId, @PathVariable String date) {
    return ResponseEntity.ok(reportService.dailyReport(userId, date));
  }

  @GetMapping("/{user_id}/{date}")
  ResponseEntity<Boolean> isDailyNorm(@PathVariable("user_id") Long userId, @PathVariable String date) {
    return ResponseEntity.ok(reportService.isInDailyNorm(userId, date));
  }

  @GetMapping("/{user_id}")
  ResponseEntity<MealsReport> getUsersMealReport(@PathVariable("user_id") Long userId) {
    return ResponseEntity.ok(reportService.dailyMealReport(userId));
  }
}
