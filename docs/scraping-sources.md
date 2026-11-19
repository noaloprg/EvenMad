## **ATLETICO DE MADRID**

### **Matches**

#### **Url masculine**

**Masculine**

```
https://www.atleticodemadrid.com/calendario-completo-primer-equipo/
```

#### **Content**

[!IMPORTANT] The superior label is `<div class="calendar-v2">`

[!NOTE] This structure is per element.

```html
<div class="calendar-item toggler-todos active">
  <div class="header-calendar ">
    <div class="competition">
      <img
        src="/img/competicion-amis_2023.jpg?v=2"
        width="50"
        alt="Amistoso 2026"
        title="Amistoso"
      />
    </div>
    <a
      title="1-1"
      href="/postpartidos/  granada-atletico-de-madrid-femenino-05-08-2026-11-00"
    >
      <ul class="marcador">
        <li class="local">
          <dl>
            <dt>Granada</dt>
            <dd class="logo">
              <img
                src="/system/fotos/9/original/granada.png?  1724068533"
                alt="Granada"
              />
            </dd>
            <dd class="marcador">1</dd>
          </dl>
        </li>
        <li class="visitante">
          <dl>
            <dt>Atlético de Madrid Femenino</dt>
            <dd class="logo">
              <img
                src="/system/escudos/919/original/aTLETI.png? 1782085570"
                alt="Atlético de Madrid Femenino"
              />
            </dd>
            <dd class="marcador">1</dd>
          </dl>
        </li>
      </ul>
    </a>
    <div class="info-calendario">
      <span>Amistoso<i> -</i> </span>
      <span>Miércoles 8 de agosto<i> -</i> </span>
      <span class="black">11:00<i> -</i> </span>
      <span class="black">PFC Costa del Sol</span>

      <span class="action-state pre-dir-pos cal-postpartidos">
        <a
          href="/postpartidos/ granada-atletico-de-madrid-femenino-05-08-2026-11-00"
          title="Resumencal-postpartidos"
        >
          Resumen
        </a>
      </span>
    </div>
  </div>
</div>
```

**Image**

[!NOTE] format in which is displayed and receved dates from HTML

<img src="https://github.com/user-attachments/assets/84a53c01-6056-4a5c-9362-6bdac281dab0" alt="how is displayed content for matches in the web" width=2000/>

### **Concerts**

#### **Url**

```
https://www.riyadhairmetropolitano.com/programacion
```

#### **Content**

[!IMPORTANT] The superior label is `u-display-contents w-dyn-items`

[!NOTE] This structure is per element. And attr `data-cat` is the one filtering the web content

```html
<div role="listitem" class="u-display-contents w-dyn-item">
  <div data-fecha="Confirmada" data-cat="Música" data-venta="Disponible" class="prog_card">
    <img
      src="https://cdn.prod.website-files.com/6a32c8960cdb22588259218f/6a3e08eaa58d546e4cc5b7a3_67863c68607e0198b14bcdee_6734f8d4d74b33198182d003_rollingstones700x700.png"
      loading="lazy"
      alt=""
      class="prog_card-img"
    />
    <div class="prog_panel">
      <div class="overlay_texto">
        <h3 class="overlay_titulo">The Rolling Stones</h3>
        <p class="overlay_fechas">1 · JUNIO · 2022</p>
      </div>
      <div class="overlay_botones">
        <div class="button est-btn-disabled is-agotado">
          COMPRAR
        </div>
        <div class="button est-btn-disabled is-prox">COMPRAR</div>
        <a
          data-buy="comprar"
          href="#"
          class="button button-blanco w-button"
          >COMPRAR ENTRADAS</a
        >
        <div class="button est-btn-disabled is-vip-agotado">
          COMPRAR ENTRADAS VIP
        </div>
        <a
          data-buy="vip"
          href="#"
          class="button button-outline-blanco w-button"
          >COMPRAR ENTRADAS VIP</a
        ><a
          href="/eventos/rolling-stones"
          class="button button-outline-blanco w-button"
          >MÁS INFORMACIÓN</a
        >
      </div>
    </div>
    <div class="est-badges">
      <div class="est-pill is-agotado">Sold-out</div>
      <div class="est-pill is-prox">Próximamente</div>
      <div class="est-pill is-tbc">POR CONFIRMAR</div>
    </div>
    <div data-ev-slug="1" class="dato-oculto">rolling-stones</div>
  </div>
</div>
```

**Image**

[!NOTE] format in which is displayed and receved dates from HTML

- Each date is separated by `|`
- Each data from date is divided by `·`

<img alt="how is displayed content for concerts in the web" src="https://github.com/user-attachments/assets/22b35b3c-d6ec-4860-a1cc-e7b52495aea9" height=100 />
