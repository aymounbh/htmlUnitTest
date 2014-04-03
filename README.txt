la problématique consiste en l'étude de possiblité d'améliorer les performances de Tanaguru lors de l'aidit
de page.

les pistes possibles:
    1- utilisation d'un pool d'instances firefox pour éviter d'en créer une instance à chaque fois
       et de réutiliser les instances déjà créées.

       => les développeurs de Tanaguru ont commencé à implémenter un pool mais le code est commenté, et
          manque de tests unitaires et de charge pour valider l'implémentation
       => j'ai commencé à implémenter quelques test dans le projet "sebuilder-interpreter-tools" aavec
          la classe "FirefoxDriverObjectPoolTest". Cette classe n'est pas encore mise au moins. Il s'agit
          d'étudier le comportement de l'application et la création des instances

    2- chercher une alternative à firefox:

      => le driver HtmlUnit représente une alternative à firefox
      => les tests menés jusque là dans la classe "HtmlUnitDriverTest"  montre qu'avec une configuration
         par défaut le driver HtmlUnit et 2 à 3 fois plus rapide que le driver firefox, mais la problématique
         est que le driver HtmlUnit ne supporte pas javascript par défaut et donc le html généré n'est pas le
         même entre les deux drivers
      => en procédant à un changement de configuration du driver pour émuler au mieux firefox avec
         un support javascript activé la tendance de création d'instances s'inverse, HtmlUnit devient
         2 fois plus lent que firefox

     - une autre possible piste serai de voir du côté de phontomeJs (pas encore exploré)

     - autre possiblité selenium grid2



