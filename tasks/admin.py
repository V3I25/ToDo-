from django.contrib import admin

from .models import Subtask, Task


class SubtaskInline(admin.TabularInline):
    model = Subtask
    extra = 0


@admin.register(Task)
class TaskAdmin(admin.ModelAdmin):
    list_display = ("title", "status", "progress", "plan_start", "plan_end", "reminder_at")
    list_filter = ("status",)
    search_fields = ("title", "description")
    inlines = [SubtaskInline]
