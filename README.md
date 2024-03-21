# WTP Java/JakartaEE Tools SUKKIRIZED ver
このプロジェクトは、Eclipse財団の公式WTPレポジトリからフォークし、
主にServlet/Filter/Listenerに関する近時の使用に伴う不具合を修正したものです。

書籍「[スッキリわかるサーブレット&JSP入門 第4版](https://www.amazon.co.jp/dp/4295018783/?tag=flairlink-22&linkCode=ogi&th=1&psc=1)」のために、開発・公開されるものですが、AS-IS 非保証の範囲で自由にご利用ください。
![スッキリわかるサーブレット&JSP入門 第4版](https://sukkiri.jp/wp-content/uploads/2024/03/sjava_banner_servlet4.webp "スッキリわかるサーブレット&JSP入門 第4版")

## 前提環境

このレポジトリは、Eclipse2023-12で使用されているバージョンのWTP(v3.32(=R3_32))から分岐しています。その前後のバージョンでの利用については、失敗する可能性があります。

動作確認がとれているバージョン
 - R3_33
 - R3_32

## 導入により修正されるWTPの動作

このレポジトリの成果物を後述の方法でインストールすると、公式WTPの以下のような動作が修正されます。

### 動的Webモジュール5.0, 6.0の選択時に、アノテーションがつき、web.xmlが生成されなくなります
公式WTPでは、動的Webモジュールが5.0以上のとき、ウィザード経由で新規作成されるServlet/Filter/Listenerにアノテーションが付与されず、かわりにweb.xmlが生成されることがありました。

この動作は、動的Webモジュールバージョン4.0以前と変化してしまっており、また、「web.xmlとアノテーションの意図しない併用」によりトラブルになることがあることから、5.0以上でも従前同様にアノテーションを利用するように変更しています。

### 動的Webモジュール5.0, 6.0の選択時に、Filterの新規作成で正しいimport文が生成されるようになります
公式WTPでは、Filterの生成時に必ず「javax.〜」が利用されます。

そのため、動的Webモジュールバージョンが5.0以上のとき（JakartaEE10(Tomcat10)使用時）は、パッケージ名の違いから生成コードにコンパイルエラーが発生します。

この修正により、正しいimport文が生成されるようになります。

### Filterの新規作成時、Filterインタフェースへの不要なimplements句が生成されないようになります
公式WTPでは、Filterの新規作成時に、必ず「implements XXX.servlet.Filter」が付記されます。

しかし、親クラスが指定されていて、かつ、そのクラスがFilterインタフェースを実装しているときは、implementsは不要であることから、生成しなくしました。

なお、親クラスの指定がない場合は、生成します。

### Servlet/Filterの新規作成時、親クラスの変更で自動追加されるインタフェースは、javax/jakartaで適切に選択されるようになります
公式WTPでは、Servletの新規作成ウィザードの1ページ目で親クラスを変更すると、3ページ目のインタフェースとして、「javax.servlet.http.Servlet」が強制追加されます。

そのため、動的Webモジュールバージョン5.0以降で使用しているときは、エラーがでていました。

この修正によりjakarta使用時には、「jakarta.servlet.http.Servlet」が強制追加されるようになります。

なお、同様の動作はFilterの新規作成ウィザードでも実装されています。

### Filterの新規作成時、親クラスとして、自動的に適切なクラスが指定されるようになります
公式WTPでは、Filterの新規作成時、「親クラスがなく、Filterインタフェースを実装する」形となっていました。

しかし、動的Webモジュール4.0以降ではHttpFilterを利用可能であるため、こちらが親クラスとして選択されるようになっています。

また親クラスが使用される場合には、init()とdestroy()のオーバーライド用スタブはデフォルトでは生成されなくなりました（ウィザード3ページ目でチェックをつけることで生成可能です）。

### Filterの新規作成ウィザードで機能していなかった「自動生成するスタブ」の指定が、正しく動作するようになります
公式WTPでは、Filterの新規作成ウィザードの3ページ目にある「自動生成するスタブ」にあるチェックボックスが機能していませんでした。

特に、「継承した抽象メソッド」を一括で有効/無効にするチェックボックスは全く機能していなかったため、正しく機能するように変更しました。

### 動的Webモジュール4.0以下の選択時、Filterの新規作成ウィザードは親クラスの実在検証を一部行わないようになります
「JakartaEE10(Tomcat10) + 動的Webモジュール4.0」などの組み合わせのとき、公式WTPでは、Filterの新規作成ウィザードで「親クラスが存在しない」旨のエラーにより、途中から進むことができません。

これは、ウィザード内の親クラスに、「javax.servlet.Filter」が設定される一方、クラスパスには「jakarta.servlet.Filter」しか存在しないためです。

本来、このエラーは正当であり、「JakartaEE10(Tomcat10) + 動的Webモジュール4.0」のような組み合わせ自体が異常です。

しかし、Servletの新規作成ウィザードでは上記エラーが表示されず、「javax.servlet.http.HttpServlet」を親クラスとするコードが生成され、CTRL+SHIFT+Oによりパッケージ名を訂正することができることから、前記、「動的Webモジュール6.0でjakarta形式のサーブレットを生成できない」という問題を回避する方法として一部で利用されることがありました。

この修正は、Filterについても同様の回避方法を使うことができるようにするためのものです。具体的には、親クラスが「javax〜」でも「jakarta〜」でも、フィルタクラスであれば、新規作成を強行できるようにしています。

公式WTPのFilter新規作成ウィザードでは機能していた「親クラスの妥当性検証」を一部スキップしていることから、本来はあまり望ましいものではありません。将来、当パッチが公式に取り込まれるなどして、前記の回避方法が巷で利用されることが十分現象したときに、正しい方向に解消されるべきものです。

## 導入方法
以下の手順で、当WTPを導入できます。

1. 起動している場合、Eclipseを終了します。
1. 当プロジェクトのルートフォルダで`mvn package`すると、以下の相対パスにjarファイルができます。<sup>[※1](#note1)</sup>
  * `plugins/org.eclipse.jst.j2ee.web/target/org.eclipse.jst.j2ee.web-1.3.0-SNAPSHOT.jar`
  * `plugins/org.eclipse.jst.servlet.ui/target/org.eclipse.jst.servlet.ui-1.2.0-SNAPSHOT.jar`

1. Eclipseの導入フォルダ（Macの場合は.appの内部のContentsフォルダ内のEclipseフォルダ）を基準として、`/dropins/WTP/eclipse/plugins`フォルダに存在する以下の2つのファイルを削除（またはどこかに退避）し、かわりに、前の手順で生成した2つのjarファイルを配置します（●●●はバージョン番号）。
    * `org.eclipse.jst.j2ee.web-1.3.0.●●●`
    * `org.eclipse.jst.servlet.ui-1.2.0.●●●.jar`
1. Eclipseをクリーンモードで起動します（上記作業をしてはじめての時だけで十分です）。
  * Eclipseの場合、-cleanオプションをつけて起動します。
  * Pleiadesの場合、Windowsならクリーン起動用のシェル（eclipse.exe -clean.cmd）が準備されています。Macの場合、`open -a "Eclipse_2023-12" --args "-clean"`で起動できます。

<small id="note1">※1: 実行結果の最後に、次のメッセージが表示されますが、問題ありません。
```
Exception in thread "Thread-2" java.lang.NoClassDefFoundError: org/eclipse/jgit/internal/JGitText
      at org.eclipse.jgit.internal.util.ShutdownHook.cleanup(ShutdownHook.java:85)
      at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.lang.ClassNotFoundException: org.eclipse.jgit.internal.JGitText
      ... 2 more
```
</small>

## ライセンス
EPL v2.0を継承します。
