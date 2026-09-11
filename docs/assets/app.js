// ← / → 在章节之间翻页（读取页面底部 .pager 里的上一章/下一章链接）
(function () {
  var pager = document.querySelector('.pager');
  if (!pager) return;
  var prev = pager.querySelector('a:not(.next)');
  var next = pager.querySelector('a.next');
  document.addEventListener('keydown', function (e) {
    if (e.metaKey || e.ctrlKey || e.altKey || e.shiftKey) return;
    var tag = (e.target && e.target.tagName) || '';
    if (tag === 'INPUT' || tag === 'TEXTAREA' || tag === 'SELECT') return;
    if (e.key === 'ArrowRight' && next) location.href = next.href;
    if (e.key === 'ArrowLeft' && prev) location.href = prev.href;
  });
})();
