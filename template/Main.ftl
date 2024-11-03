<div>
    <h3>Click trigger</h3>
    <button hx-get="<@ofbizUrl>sayDate</@ofbizUrl>" hx-target="#responseDiv" hx-swap="outerHTML">
        Say Date
    </button>
    <div id="responseDiv">
    </div>
</div>
<div>
<hr/>
<h3>Server time</h3>
    <div id="serverTime" hx-get="<@ofbizUrl>sayDate</@ofbizUrl>" hx-trigger="every 3s" hx-swap="innerHTML">
    </div>
</div>
<hr />
<h3>Cascading Select</h3>
<div hx-get="<@ofbizUrl>countries</@ofbizUrl>" hx-trigger="load delay:1s" hx-target="#cntrLst" hx-swap="innerHTML">
    <select id="cntrLst" name="countryId" hx-post="<@ofbizUrl>regions</@ofbizUrl>"
        hx-trigger="change delay:0.500s" hx-target="#regList" hx-swap="innerHTML">
    </select>
    <select id="regList" name="regId">
    </select>
</div>