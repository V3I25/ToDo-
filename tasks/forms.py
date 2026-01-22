from django import forms

from .models import Subtask, Task


class TaskForm(forms.ModelForm):
    reminder_at = forms.DateTimeField(
        required=False,
        widget=forms.DateTimeInput(attrs={"type": "datetime-local"}),
        label="Напоминание",
    )
    plan_start = forms.DateField(
        required=False,
        widget=forms.DateInput(attrs={"type": "date"}),
        label="Плановая дата начала",
    )
    plan_end = forms.DateField(
        required=False,
        widget=forms.DateInput(attrs={"type": "date"}),
        label="Плановая дата завершения",
    )

    class Meta:
        model = Task
        fields = [
            "title",
            "description",
            "status",
            "progress",
            "plan_start",
            "plan_end",
            "reminder_at",
        ]


class SubtaskForm(forms.ModelForm):
    class Meta:
        model = Subtask
        fields = ["title", "progress", "is_done"]
