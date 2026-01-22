from django.db.models import Avg
from django.shortcuts import get_object_or_404, redirect, render
from django.utils import timezone

from .forms import SubtaskForm, TaskForm
from .models import Subtask, Task


def task_list(request):
    tasks = Task.objects.all().prefetch_related("subtasks")
    now = timezone.now()
    return render(
        request,
        "tasks/task_list.html",
        {
            "tasks": tasks,
            "now": now,
        },
    )


def task_detail(request, task_id):
    task = get_object_or_404(Task, pk=task_id)
    subtasks = task.subtasks.all()
    subtask_progress = subtasks.aggregate(avg=Avg("progress"))["avg"]
    subtask_progress = round(subtask_progress) if subtask_progress is not None else None
    return render(
        request,
        "tasks/task_detail.html",
        {
            "task": task,
            "subtasks": subtasks,
            "subtask_form": SubtaskForm(),
            "subtask_progress": subtask_progress,
        },
    )


def task_create(request):
    if request.method == "POST":
        form = TaskForm(request.POST)
        if form.is_valid():
            task = form.save()
            return redirect("tasks:detail", task_id=task.id)
    else:
        form = TaskForm()
    return render(request, "tasks/task_form.html", {"form": form, "action": "Создать"})


def task_update(request, task_id):
    task = get_object_or_404(Task, pk=task_id)
    if request.method == "POST":
        form = TaskForm(request.POST, instance=task)
        if form.is_valid():
            form.save()
            return redirect("tasks:detail", task_id=task.id)
    else:
        form = TaskForm(instance=task)
    return render(request, "tasks/task_form.html", {"form": form, "action": "Сохранить"})


def subtask_create(request, task_id):
    task = get_object_or_404(Task, pk=task_id)
    if request.method == "POST":
        form = SubtaskForm(request.POST)
        if form.is_valid():
            subtask = form.save(commit=False)
            subtask.task = task
            subtask.save()
    return redirect("tasks:detail", task_id=task.id)


def subtask_toggle(request, task_id, subtask_id):
    task = get_object_or_404(Task, pk=task_id)
    subtask = get_object_or_404(Subtask, pk=subtask_id, task=task)
    subtask.is_done = not subtask.is_done
    if subtask.is_done:
        subtask.progress = 100
    subtask.save()
    return redirect("tasks:detail", task_id=task.id)
