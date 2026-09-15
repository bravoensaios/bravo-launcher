# Bravo Launcher — teste 1

Aplicativo auxiliar para Android TV. Ele NÃO renderiza o ScreenHub: abre a URL do wrapper no Chrome e usa um AccessibilityService para clicar no botão `ABRIR SCREENHUB` quando o Chrome o expõe na árvore de acessibilidade.

Esta primeira versão NÃO inicia no boot. Isso é intencional para testar com segurança.

## Compilar no GitHub
1. Crie um repositório e envie todo o conteúdo desta pasta, inclusive `.github`.
2. Abra a aba Actions e execute `Build Bravo Launcher APK` (um push em main também dispara a build).
3. Baixe o artifact `bravo-launcher-debug-apk` e extraia `app-debug.apk`.

## Instalar por ADB
`./adb -s 192.168.1.4:46861 install -r app-debug.apk`

## Teste
Abra Bravo Launcher na TV. Primeiro escolha `ATIVAR ACESSIBILIDADE` e habilite Bravo Launcher. Volte ao app e escolha `ABRIR SCREENHUB NO CHROME`. Se o botão da página estiver visível na árvore de acessibilidade, o serviço o clicará e o Chrome entrará em fullscreen.

Se este teste funcionar, a próxima versão adicionará o fluxo de inicialização automática.
