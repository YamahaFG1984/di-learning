/* ================================================================
   依赖注入十一讲 · 共享脚本：侧边栏、目录、代码高亮、主题、翻页
   ================================================================ */
// 保持经典 <script> 而不是 type="module"：模块脚本在 file:// 下会被浏览器拦截，
// 而这些页面经常是直接双击打开的。
(() => {
  'use strict';

  const CHAPTERS = [
    { id: 'ch00', k: '00', tag: '导读',    t: '导读与全书地图',     d: '这本 2009 年的书为什么今天仍值得读、本讲义怎么读、什么变了什么没变', part: '开始' },
    { id: 'ch01', k: '01', tag: '第 1 章', t: '依赖注入到底解决什么', d: '手工构造 → 工厂 → 服务定位器 → 注入器，四步演进与各自的死穴', part: '第一部分 · 原理' },
    { id: 'ch02', k: '02', tag: '第 2 章', t: '动手：注入器与键',    d: 'bootstrap 时机、五种配置方式、字符串键/类型键/组合键的取舍' },
    { id: 'ch03', k: '03', tag: '第 3 章', t: '注入方式的取舍',      d: '构造器 vs setter、构造器金字塔、循环引用、Provider / Assisted / Builder' },
    { id: 'ch04', k: '04', tag: '第 4 章', t: '模块化与可测试性',    d: '紧耦合的真实代价、面向契约编程、单元测试与集成测试的分工' },
    { id: 'ch05', k: '05', tag: '第 5 章', t: '作用域：状态的容器',  d: 'no scope / singleton / request / session，以及"单例反模式"为何是反模式', part: '第二部分 · 状态与行为' },
    { id: 'ch06', k: '06', tag: '第 6 章', t: '自定义作用域与陷阱',  d: '手写事务作用域，以及 scope-widening 引发的三类事故' },
    { id: 'ch07', k: '07', tag: '第 7 章', t: '对象生命周期',        d: '构造/终结、领域相关的生命周期、析构反模式、后处理与多播' },
    { id: 'ch08', k: '08', tag: '第 8 章', t: '拦截、代理与 AOP',    d: '代理原理、事务与安全的声明式实现、代理拦不到的五类成员' },
    { id: 'ch09', k: '09', tag: '第 9 章', t: '并发、可见性与设计',  d: '安全发布、五道不可变性谜题、同步 vs 并发、包级封装', part: '第三部分 · 工程' },
    { id: 'ch10', k: '10', tag: '第 10 章', t: '与第三方框架集成',   d: '刚性配置反模式、脆弱基类、程序化配置为何是正解' },
    { id: 'ch11', k: '11', tag: '第 11 章', t: '实战：Crosstalk',    d: '一个微博客的完整分层，把前十章的模式串起来' },
    { id: 'appendix-a', k: 'A', tag: '附录 A', t: '2009 → 2026 迁移总表', d: '读书时常驻脑中的替换表：javax→jakarta、XML→Java 配置、停更库的替代品，以及被推翻的结论', part: '附录' },
    { id: 'appendix-b', k: 'B', tag: '附录 B', t: '其他容器与术语表',  d: 'Butterfly Container 与 SmartyPants 的 DSL 配置思路，外加全书术语速查' }
  ];

  const href = (c) => `${c.id}.html`;
  const page = document.body.dataset.page || 'index';
  const idx = CHAPTERS.findIndex((c) => c.id === page);
  const esc = (s) => s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');

  /* ---------- 侧边栏 ---------- */
  const side = document.getElementById('sidebar');
  if (side) {
    const brand = '<a class="brand" href="index.html"><span class="logo">DI</span>' +
      '<span>依赖注入十一讲<small>Spring &amp; Guice · 2009 → 2026</small></span></a>';
    const home = `<a class="ch${page === 'index' ? ' active' : ''}" href="index.html"><span class="n">&#8962;</span><span>目录</span></a>`;
    const links = CHAPTERS.map((c, i) =>
      (c.part ? `<div class="part">${c.part}</div>` : '') +
      `<a class="ch${i === idx ? ' active' : ''}" href="${href(c)}"${i === idx ? ' aria-current="page"' : ''}>` +
      `<span class="n">${c.k}</span><span>${esc(c.t)}</span></a>`
    );
    side.innerHTML = brand + home + links.join('');
    const active = side.querySelector('a.ch.active');
    if (active) setTimeout(() => active.scrollIntoView({ block: 'center' }), 0);
  }

  /* ---------- 移动端菜单 ---------- */
  const btn = Object.assign(document.createElement('button'), {
    id: 'menu-btn',
    type: 'button',
    innerHTML: '&#9776;',
    onclick: () => document.body.classList.toggle('nav-open')
  });
  btn.setAttribute('aria-label', '目录');
  document.body.appendChild(btn);
  document.addEventListener('click', (e) => {
    if (document.body.classList.contains('nav-open') &&
        side && !side.contains(e.target) && e.target !== btn) {
      document.body.classList.remove('nav-open');
    }
  });

  /* ---------- 主题切换 ---------- */
  // 保存的主题在 <head> 里的内联脚本中就已应用，避免翻页时闪一下；这里只负责按钮
  const THEME_KEY = 'di-doc-theme';
  const tbtn = Object.assign(document.createElement('button'), {
    id: 'theme-btn',
    type: 'button',
    innerHTML: '&#9789;',
    onclick: () => {
      const root = document.documentElement;
      const now = root.getAttribute('data-theme');
      const dark = now ? now === 'dark'
        : window.matchMedia('(prefers-color-scheme: dark)').matches;
      const next = dark ? 'light' : 'dark';
      root.setAttribute('data-theme', next);
      try { localStorage.setItem(THEME_KEY, next); } catch {}
    }
  });
  tbtn.setAttribute('aria-label', '切换深浅色');
  document.body.appendChild(tbtn);

  /* ---------- 章节内目录 ---------- */
  const main = document.querySelector('main');
  const slot = document.getElementById('chapter-toc');
  if (slot && main) {
    const hs = [...main.querySelectorAll('h2')];
    if (hs.length > 2) {
      const items = hs.map((h, i) => {
        if (!h.id) h.id = `sec-${i + 1}`;
        const sec = h.querySelector('.sec');
        const title = sec ? h.textContent.slice(sec.textContent.length) : h.textContent;
        return `<li><a href="#${h.id}">${sec ? `<span class="n">${sec.textContent}</span>` : ''}${esc(title)}</a></li>`;
      });
      slot.className = 'toc';
      slot.innerHTML = `<div class="h">本章目录</div><ol>${items.join('')}</ol>`;
    }
  }

  /* ---------- 代码块：语言标签 + 复制 + 高亮 ---------- */
  // 代码块里作者手写了 <i>（注释）和 <b>（重点）标记，所以只给文本节点上色，不重写整段 HTML
  const KW = ('public|private|protected|static|final|abstract|class|interface|enum|record|extends|implements|' +
    'new|return|if|else|for|while|do|try|catch|finally|throw|throws|switch|case|default|break|continue|' +
    'import|package|void|boolean|int|long|double|float|char|byte|short|var|this|super|null|true|false|' +
    'synchronized|volatile|transient|instanceof|sealed|permits|yield').split('|');

  // 每一段用正则字面量写，取 .source 拼起来，省掉字符串里的双重转义
  const RE_JAVA = new RegExp([
    /(\/\*[\s\S]*?\*\/|\/\/[^\n]*)/.source,                                   // 1 注释
    /("(?:\\[\s\S]|[^\\"\n])*"|'(?:\\[\s\S]|[^\\'\n])')/.source,               // 2 字符串
    String.raw`\b(${KW.join('|')})\b`,                                        // 3 关键字
    /(@[A-Za-z_][\w.]*)/.source,                                              // 4 注解
    /\b([A-Z][A-Za-z0-9_]*)\b/.source,                                        // 5 类型
    /\b(\d+(?:\.\d+)?[LlFfDd]?)\b/.source,                                    // 6 数字
    /\b([a-z_$][\w$]*)(?=\()/.source                                          // 7 方法调用
  ].join('|'), 'g');
  const JAVA_CLASSES = ['tk-cm', 'tk-st', 'tk-kw', 'tk-an', 'tk-tp', 'tk-nm', 'tk-fn'];

  const RE_XML = /(<!--[\s\S]*?-->)|("[^"\n]*")|(<\/?[\w:.-]+|\/?>)|([\w:.-]+)(?==)/g;
  const XML_CLASSES = ['tk-cm', 'tk-st', 'tk-kw', 'tk-tp'];

  const RE_TEXT = /(#[^\n]*)|("[^"\n]*")/g;
  const TEXT_CLASSES = ['tk-cm', 'tk-st'];

  const RULES = { java: [RE_JAVA, JAVA_CLASSES], xml: [RE_XML, XML_CLASSES] };

  function paint(src, re, classes) {
    let out = '';
    let last = 0;
    // 所有分支都至少匹配一个字符，matchAll 不会卡在零宽匹配上
    for (const m of src.matchAll(re)) {
      out += esc(src.slice(last, m.index));
      const g = m.findIndex((v, i) => i > 0 && v !== undefined);
      out += `<span class="${classes[g - 1]}">${esc(m[g])}</span>`;
      last = m.index + m[0].length;
    }
    return out + esc(src.slice(last));
  }

  function highlight(pre, re, classes) {
    const walker = document.createTreeWalker(pre, NodeFilter.SHOW_TEXT, {
      acceptNode: (n) => (n.parentElement.closest('i') ? NodeFilter.FILTER_REJECT : NodeFilter.FILTER_ACCEPT)
    });
    const nodes = [];
    while (walker.nextNode()) nodes.push(walker.currentNode);
    for (const node of nodes) {
      const tpl = document.createElement('template');
      tpl.innerHTML = paint(node.data, re, classes);
      node.replaceWith(tpl.content);
    }
  }

  document.querySelectorAll('.code').forEach((box) => {
    const pre = box.querySelector('pre');
    if (!pre) return;
    const { lang = '', file = '' } = box.dataset;

    const bar = document.createElement('div');
    bar.className = 'bar';
    bar.innerHTML = `<span class="tag">${esc(file || lang || 'code')}</span>`;
    const cp = Object.assign(document.createElement('button'), {
      className: 'copy',
      type: 'button',
      textContent: '复制'
    });
    cp.onclick = () => {
      navigator.clipboard?.writeText(pre.textContent);
      cp.textContent = '已复制';
      setTimeout(() => { cp.textContent = '复制'; }, 1400);
    };
    bar.appendChild(cp);
    box.insertBefore(bar, pre);

    const [re, classes] = RULES[lang] || [RE_TEXT, TEXT_CLASSES];
    highlight(pre, re, classes);
  });

  /* ---------- 上一章 / 下一章 ---------- */
  const pager = document.getElementById('pager');
  const prev = idx > 0 ? CHAPTERS[idx - 1] : null;
  const next = idx >= 0 ? CHAPTERS[idx + 1] : null;
  if (pager && idx >= 0) {
    const prevLink = prev
      ? `<a class="prev" href="${href(prev)}"><span>&larr; 上一章</span>${prev.tag} · ${esc(prev.t)}</a>`
      : '<a class="prev" href="index.html"><span>&larr; 返回</span>目录</a>';
    const nextLink = next
      ? `<a class="next" href="${href(next)}"><span>下一章 &rarr;</span>${next.tag} · ${esc(next.t)}</a>`
      : '<a class="next ph" href="#">.</a>';
    pager.className = 'pager';
    pager.innerHTML = prevLink + nextLink;
  }

  // ← / → 在章节之间翻页
  document.addEventListener('keydown', (e) => {
    if (idx < 0 || e.metaKey || e.ctrlKey || e.altKey || e.shiftKey) return;
    if (e.target.closest?.('input, textarea, select, [contenteditable]')) return;
    if (e.key === 'ArrowRight' && next) location.href = href(next);
    if (e.key === 'ArrowLeft') location.href = prev ? href(prev) : 'index.html';
  });

  /* ---------- 首页目录 ---------- */
  const grid = document.getElementById('toc-grid');
  if (grid) {
    let html = '';
    CHAPTERS.forEach((c, i) => {
      if (c.part) html += `${i ? '</div>' : ''}<h3 class="toc-part">${c.part}</h3><div class="toc-grid">`;
      html += `<a class="toc-card" href="${href(c)}">` +
        `<div class="n">${c.tag}</div>` +
        `<div class="t">${esc(c.t)}</div>` +
        `<div class="d">${esc(c.d)}</div></a>`;
    });
    grid.innerHTML = html + '</div>';
  }
})();
