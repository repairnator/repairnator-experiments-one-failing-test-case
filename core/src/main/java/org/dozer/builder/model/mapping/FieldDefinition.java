/*
 * Copyright 2005-2017 Dozer Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dozer.builder.model.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dozer.MappingException;
import org.dozer.builder.model.AbstractDefinition;
import org.dozer.builder.model.configuration.Relationship;
import org.dozer.classmap.ClassMap;
import org.dozer.classmap.MappingDirection;
import org.dozer.classmap.RelationshipType;
import org.dozer.config.BeanContainer;
import org.dozer.fieldmap.CustomGetSetMethodFieldMap;
import org.dozer.fieldmap.DozerField;
import org.dozer.fieldmap.FieldMap;
import org.dozer.fieldmap.GenericFieldMap;
import org.dozer.fieldmap.HintContainer;
import org.dozer.fieldmap.MapFieldMap;
import org.dozer.util.MappingUtils;

/**
 * Specifies a custom field mapping. Fields that share the same attribute name do not need to be defined.
 * Dozer automatically maps fields that match on attribute name. All Field Mapping definitions are bi-directional
 * by default.
 * Global configuration, Mapping, and ClassDefinition element values are inherited.
 * <p>
 * Hints are used for mapping Collection types. A hint indicates which type of destination object should be created
 * and added to the
 * destination Collection.
 * <p>
 * Required Attributes:
 * <p>
 * Optional Attributes:
 * <p>
 * relationship-type For collections, indicates whether to add to existing values or to always replace any existing
 * entries
 * <p>
 * remove-orphans For collections, indicates whether items that did not exist in the source collection should be
 * removed from the destination collection.
 * <p>
 * type Indicates whether this mapping is bi-directional or only one-way. Typically this will be set to bi-directional. The default is "bi-directional".
 * <p>
 * map-id The id that uniquely identifies this mapping definition. This typically will not be specified.
 * You would only need to specify this for only need this for special context based mapping
 * and when mapping between Map objects and Custom Data Objects.
 * <p>
 * copy-by-reference Indicates whether the source field value is copied by reference or by value when populating the destination field. The default value is "false"
 * <p>
 * custom-converter Indicates that a specific custom converter should be used for mapping this field. Typically
 * this will not be specified.
 */
@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
public class FieldDefinition extends AbstractDefinition {

    @XmlTransient
    private MappingDefinition parent;

    @XmlTransient
    private FieldExcludeDefinition fieldExcludeParent;

    @XmlElement(required = true)
    private FieldDefinitionDefinition a;

    @XmlElement(required = true)
    private FieldDefinitionDefinition b;

    @XmlElement(name = "a-hint")
    private String aHint;

    @XmlElement(name = "b-hint")
    private String bHint;

    @XmlElement(name = "a-deep-index-hint")
    private String aDeepIndexHint;

    @XmlElement(name = "b-deep-index-hint")
    private String bDeepIndexHint;

    @XmlAttribute(name = "relationship-type")
    private Relationship relationshipType;

    @XmlAttribute(name = "remove-orphans")
    private Boolean removeOrphans;

    @XmlAttribute(name = "type")
    private Type type;

    @XmlAttribute(name = "map-id")
    private String mapId;

    @XmlAttribute(name = "copy-by-reference")
    private Boolean copyByReference;

    @XmlAttribute(name = "custom-converter")
    private String customConverter;

    @XmlAttribute(name = "custom-converter-id")
    private String customConverterId;

    @XmlAttribute(name = "custom-converter-param")
    private String customConverterParam;

    public FieldDefinition() {
        this(null, null);
    }

    public FieldDefinition(MappingDefinition parent, FieldExcludeDefinition fieldExcludeParent) {
        this.parent = parent;
        this.fieldExcludeParent = fieldExcludeParent;

        setELEngine(BeanContainer.getInstance().getElEngine());
    }

    public FieldDefinitionDefinition getA() {
        return a;
    }

    public FieldDefinitionDefinition getB() {
        return b;
    }

    public String getAHint() {
        return aHint;
    }

    public String getBHint() {
        return bHint;
    }

    public String getADeepIndexHint() {
        return aDeepIndexHint;
    }

    public String getBDeepIndexHint() {
        return bDeepIndexHint;
    }

    public Relationship getRelationshipType() {
        return relationshipType;
    }

    public Boolean getRemoveOrphans() {
        return removeOrphans;
    }

    public Type getType() {
        return type;
    }

    public String getMapId() {
        return mapId;
    }

    public Boolean getCopyByReference() {
        return copyByReference;
    }

    public String getCustomConverter() {
        return customConverter;
    }

    public String getCustomConverterId() {
        return customConverterId;
    }

    public String getCustomConverterParam() {
        return customConverterParam;
    }

    public void setA(FieldDefinitionDefinition a) {
        this.a = a;
    }

    public void setB(FieldDefinitionDefinition b) {
        this.b = b;
    }

    public void setAHint(String aHint) {
        this.aHint = aHint;
    }

    public void setBHint(String bHint) {
        this.bHint = bHint;
    }

    public void setADeepIndexHint(String aDeepIndexHint) {
        this.aDeepIndexHint = aDeepIndexHint;
    }

    public void setBDeepIndexHint(String bDeepIndexHint) {
        this.bDeepIndexHint = bDeepIndexHint;
    }

    public void setRelationshipType(Relationship relationshipType) {
        this.relationshipType = relationshipType;
    }

    public void setRemoveOrphans(Boolean removeOrphans) {
        this.removeOrphans = removeOrphans;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public void setCopyByReference(Boolean copyByReference) {
        this.copyByReference = copyByReference;
    }

    public void setCustomConverter(String customConverter) {
        this.customConverter = customConverter;
    }

    public void setCustomConverterId(String customConverterId) {
        this.customConverterId = customConverterId;
    }

    public void setCustomConverterParam(String customConverterParam) {
        this.customConverterParam = customConverterParam;
    }

    // Fluent API
    //-------------------------------------------------------------------------
    public FieldDefinitionDefinition withA() {
        FieldDefinitionDefinition a = new FieldDefinitionDefinition(fieldExcludeParent, this);
        setA(a);

        return a;
    }

    public FieldDefinitionDefinition withB() {
        FieldDefinitionDefinition b = new FieldDefinitionDefinition(fieldExcludeParent, this);
        setB(b);

        return b;
    }

    public FieldDefinition withAHint(String aHint) {
        setAHint(aHint);

        return this;
    }

    public FieldDefinition withBHint(String bHint) {
        setBHint(bHint);

        return this;
    }

    public FieldDefinition withADeepIndexHint(String aDeepIndexHint) {
        setADeepIndexHint(aDeepIndexHint);

        return this;
    }

    public FieldDefinition withBDeepIndexHint(String bDeepIndexHint) {
        setBDeepIndexHint(bDeepIndexHint);

        return this;
    }

    public FieldDefinition withRelationshipType(Relationship relationshipType) {
        setRelationshipType(relationshipType);

        return this;
    }

    public FieldDefinition withRemoveOrphans(Boolean removeOrphans) {
        setRemoveOrphans(removeOrphans);

        return this;
    }

    public FieldDefinition withType(Type type) {
        setType(type);

        return this;
    }

    public FieldDefinition withMapId(String mapId) {
        setMapId(mapId);

        return this;
    }

    public FieldDefinition withCopyByReference(Boolean copyByReference) {
        setCopyByReference(copyByReference);

        return this;
    }

    public FieldDefinition withCustomConverter(String customConverter) {
        setCustomConverter(customConverter);

        return this;
    }

    public FieldDefinition withCustomConverterId(String customConverterId) {
        setCustomConverterId(customConverterId);

        return this;
    }

    public FieldDefinition withCustomConverterParam(String customConverterParam) {
        setCustomConverterParam(customConverterParam);

        return this;
    }

    public FieldExcludeDefinition endFieldExclude() {
        return fieldExcludeParent;
    }

    public MappingDefinition end() {
        return parent;
    }

    public FieldMap build(ClassMap classMap) {
        if (a == null || b == null) {
            throw new MappingException("Field name can not be empty");
        }

        DozerField aField = a.convert();
        DozerField bField = b.convert();

        FieldMap fieldMap = resolveFieldMapType(classMap, aField, bField);
        fieldMap.setSrcField(aField);
        fieldMap.setDestField(bField);
        if (type != null) {
            fieldMap.setType(MappingDirection.valueOf(type.value()));
        }

        if (relationshipType != null) {
            fieldMap.setRelationshipType(RelationshipType.valueOf(relationshipType.value()));
        }

        fieldMap.setRemoveOrphans(removeOrphans == null ? false : removeOrphans);

        HintContainer aHintContainer = getHintContainer(aHint);
        if (aHintContainer != null) {
            fieldMap.setSrcHintContainer(aHintContainer);
        }

        HintContainer bHintContainer = getHintContainer(bHint);
        if (bHintContainer != null) {
            fieldMap.setDestHintContainer(bHintContainer);
        }

        HintContainer aDeepHintContainer = getHintContainer(aDeepIndexHint);
        if (aDeepHintContainer != null) {
            fieldMap.setSrcDeepIndexHintContainer(aDeepHintContainer);
        }

        HintContainer bDeepHintContainer = getHintContainer(bDeepIndexHint);
        if (bDeepHintContainer != null) {
            fieldMap.setDestDeepIndexHintContainer(bDeepHintContainer);
        }

        if (copyByReference != null) {
            fieldMap.setCopyByReference(copyByReference == null ? null : copyByReference);
        }

        fieldMap.setMapId(mapId);
        fieldMap.setCustomConverter(resolveELExpression(customConverter));
        fieldMap.setCustomConverterId(customConverterId);
        fieldMap.setCustomConverterParam(customConverterParam);

        return fieldMap;
    }

    private FieldMap resolveFieldMapType(ClassMap classMap, DozerField aField, DozerField bField) {
        FieldMap answer;
        if (aField.isMapTypeCustomGetterSetterField()
            || bField.isMapTypeCustomGetterSetterField()
            || classMap.isSrcClassMapTypeCustomGetterSetter()
            || classMap.isDestClassMapTypeCustomGetterSetter()) {
            answer = new MapFieldMap(classMap);
        } else if (aField.isCustomGetterSetterField() || bField.isCustomGetterSetterField()) {
            answer = new CustomGetSetMethodFieldMap(classMap);
        } else {
            answer = new GenericFieldMap(classMap);
        }

        return answer;
    }

    private HintContainer getHintContainer(String hint) {
        HintContainer hintContainer = null;
        if (!StringUtils.isEmpty(hint)) {
            hintContainer = new HintContainer();
            hintContainer.setHintName(resolveELExpression(hint));
        }

        return hintContainer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("a", a)
            .append("b", b)
            .append("aHint", aHint)
            .append("bHint", bHint)
            .append("aDeepIndexHint", aDeepIndexHint)
            .append("bDeepIndexHint", bDeepIndexHint)
            .append("relationshipType", relationshipType)
            .append("removeOrphans", removeOrphans)
            .append("type", type)
            .append("mapId", mapId)
            .append("copyByReference", copyByReference)
            .append("customConverter", customConverter)
            .append("customConverterId", customConverterId)
            .append("customConverterParam", customConverterParam)
            .append("AHint", aHint)
            .append("BHint", bHint)
            .append("ADeepIndexHint", aDeepIndexHint)
            .append("BDeepIndexHint", bDeepIndexHint)
            .toString();
    }
}
