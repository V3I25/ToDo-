from django.core.validators import MaxValueValidator, MinValueValidator
from django.db import models
from django.utils import timezone


class Task(models.Model):
    STATUS_PLANNED = "planned"
    STATUS_IN_PROGRESS = "in_progress"
    STATUS_DONE = "done"

    STATUS_CHOICES = [
        (STATUS_PLANNED, "Запланировано"),
        (STATUS_IN_PROGRESS, "В работе"),
        (STATUS_DONE, "Завершено"),
    ]

    title = models.CharField("Название", max_length=200)
    description = models.TextField("Описание", blank=True)
    status = models.CharField(
        "Статус",
        max_length=20,
        choices=STATUS_CHOICES,
        default=STATUS_PLANNED,
    )
    progress = models.PositiveIntegerField(
        "Прогресс (%)",
        default=0,
        validators=[MinValueValidator(0), MaxValueValidator(100)],
    )
    plan_start = models.DateField("Плановая дата начала", blank=True, null=True)
    plan_end = models.DateField("Плановая дата завершения", blank=True, null=True)
    reminder_at = models.DateTimeField("Напоминание", blank=True, null=True)
    created_at = models.DateTimeField("Создано", auto_now_add=True)
    updated_at = models.DateTimeField("Обновлено", auto_now=True)

    class Meta:
        ordering = ["-created_at"]

    def __str__(self) -> str:
        return self.title

    @property
    def is_overdue(self) -> bool:
        if self.plan_end is None:
            return False
        return timezone.localdate() > self.plan_end and self.status != self.STATUS_DONE

    @property
    def plan_status(self) -> str:
        if self.plan_end is None:
            return "План не задан"
        if self.status == self.STATUS_DONE:
            return "Выполнено по плану" if not self.is_overdue else "Выполнено с опозданием"
        return "Срок нарушен" if self.is_overdue else "В рамках плана"


class Subtask(models.Model):
    task = models.ForeignKey(Task, related_name="subtasks", on_delete=models.CASCADE)
    title = models.CharField("Подзадача", max_length=200)
    is_done = models.BooleanField("Готово", default=False)
    progress = models.PositiveIntegerField(
        "Прогресс (%)",
        default=0,
        validators=[MinValueValidator(0), MaxValueValidator(100)],
    )
    created_at = models.DateTimeField("Создано", auto_now_add=True)

    class Meta:
        ordering = ["created_at"]

    def __str__(self) -> str:
        return self.title
